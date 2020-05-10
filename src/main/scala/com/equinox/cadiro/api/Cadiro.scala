package com.equinox.cadiro.api

import com.equinox.cadiro.api.models.{League, SearchQuery, SearchQueryRoot, SearchResult, Sorting, Status}
import com.equinox.cadiro.utils.{ApiHostConf, HttpNetManager}
import org.apache.http.client.methods.CloseableHttpResponse
import play.api.libs.json.Json


class Cadiro(val searchResult: SearchResult) extends PoeTrade {
  override def refresh: Option[PoeTrade] = ???
}

sealed trait SearchEntry
object Cadiro {
  def apply(closeableHttpResponse: CloseableHttpResponse): Cadiro = {
    new Cadiro(
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

  def execute(implicit ev: E =:= Cadiro.CompleteSearchQuery): Option[Cadiro] = {
    val searchQuery = SearchQuery(status.get.toStatusOption, name.get, `type`)
    val url = ApiHostConf.searchHost.concat(league.id.capitalize)
    val entity = Json.toJson(SearchQueryRoot(searchQuery, order.flatMap(_.toSortingOption))).toString()

    HttpNetManager.sr(
      HttpNetManager.createPost(url, entity),
      closeableHttpResponse => {
        Cadiro(closeableHttpResponse)
      }
    )
  }

}