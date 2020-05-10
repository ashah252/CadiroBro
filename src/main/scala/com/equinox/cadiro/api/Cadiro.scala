package com.equinox.cadiro.api

import com.equinox.cadiro.api.Cadiro.CompleteSearchQuery
import com.equinox.cadiro.api.models.{FetchResult, FetchResultRoot, League, SearchQuery, SearchQueryRoot, SearchResult}
import com.equinox.cadiro.utils.{ApiHostConf, HttpNetManager}
import org.apache.http.client.methods.CloseableHttpResponse
import play.api.libs.json.Json


class Cadiro(val cadiroBuilder: CadiroBuilder[CompleteSearchQuery], val searchResult: SearchResult) extends PoeTrade {

  protected val iterResultList: Iterator[List[String]] = searchResult.result.getOrElse(List()).grouped(ApiHostConf.pageLimit)

  override def refresh: Option[Cadiro] = cadiroBuilder.execute

  def getNext: Option[CadiroObservable] = {
    if (iterResultList.hasNext) {
      val url = ApiHostConf.fetchHost.concat(iterResultList.next().mkString(",").concat("?query=").concat(searchResult.id))
      println(url)
      HttpNetManager.sr(
        HttpNetManager.createGet(url),
        closeableHttpResponse => {
          CadiroObservable(cadiroBuilder, searchResult, closeableHttpResponse)
        })
    } else {
      None
    }
  }

}

class CadiroObservable(
                       override val cadiroBuilder: CadiroBuilder[CompleteSearchQuery],
                       override val searchResult: SearchResult,
                       val observableList: List[FetchResult])
  extends Cadiro(cadiroBuilder, searchResult) {
  observableList.foreach(println(_))
}

object CadiroObservable {
  def apply(cadiroBuilder: CadiroBuilder[CompleteSearchQuery], searchResult: SearchResult, closeableHttpResponse: CloseableHttpResponse): CadiroObservable = {

    new CadiroObservable(
      cadiroBuilder,
      searchResult,
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")
      ).as[FetchResultRoot].result
    )
  }
}

sealed trait SearchEntry
object Cadiro {

  def apply(cadiroBuilder: CadiroBuilder[CompleteSearchQuery], closeableHttpResponse: CloseableHttpResponse): Cadiro = {
    new Cadiro(
      cadiroBuilder,
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")

      ).as[SearchResult]
    )
  }


  def setLeague(league: String): CadiroBuilder[LeagueEntry] = CadiroBuilder[LeagueEntry](League(league, league))
  def setLeague(league: League): CadiroBuilder[LeagueEntry] = CadiroBuilder[LeagueEntry](league)

  sealed trait LeagueEntry extends SearchEntry
  sealed trait StatusEntry extends SearchEntry
  sealed trait NameEntry extends SearchEntry
  sealed trait TypeEntry extends SearchEntry
  sealed trait OrderEntry extends SearchEntry

  type CompleteSearchQuery = LeagueEntry with NameEntry with StatusEntry
}

case class CadiroBuilder[E <: SearchEntry](
                                            league: League,
                                            name: Option[String] = None,
                                            status: Option[Status] = None,
                                            `type`: Option[String] = None,
                                            order: Option[Sorting] = None,
                                          ) {

  // mandatory
  def search(name: String): CadiroBuilder[E with Cadiro.NameEntry] = this.copy(name = Some(name))
  def setStatus(status: Status): CadiroBuilder[E with Cadiro.StatusEntry] = this.copy(status = Some(status))

  // optional
  def setType(`type`: String): CadiroBuilder[E] = this.copy(`type` = Some(`type`))
  def setOrder(order: Sorting): CadiroBuilder[E] = this.copy(order = Some(order))


  def imprint(implicit ev: E =:= Cadiro.CompleteSearchQuery): CadiroBuilder[CompleteSearchQuery] = {
    this.asInstanceOf[CadiroBuilder[CompleteSearchQuery]]
  }

  def execute(implicit ev: E =:= Cadiro.CompleteSearchQuery): Option[Cadiro] = {
    val searchQuery = SearchQuery(status.get.toStatusOption, name.get, `type`)
    val url = ApiHostConf.searchHost.concat(league.id.capitalize.trim)
    val entity = Json.toJson(SearchQueryRoot(searchQuery, order.flatMap(_.toSortingOption))).toString()


    HttpNetManager.sr(
      HttpNetManager.createPost(url, entity),
      closeableHttpResponse => {
        Cadiro(this.imprint, closeableHttpResponse)
      }
    )
  }

}