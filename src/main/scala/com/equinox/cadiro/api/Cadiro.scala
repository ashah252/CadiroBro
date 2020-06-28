package com.equinox.cadiro.api

import com.equinox.cadiro.api.Cadiro.CompleteSearchQuery
import com.equinox.cadiro.api.filter.{CadiroFilter, Sorting, Status}
import com.equinox.cadiro.api.models.{FetchResult, FetchRootInterface, FilterList, League, SearchQuery, SearchQueryRoot, SearchResult}
import com.equinox.cadiro.utils.{ApiHostConf, CadiroLogManager, HttpNetManager}
import org.apache.http.client.methods.CloseableHttpResponse
import play.api.libs.json.Json


class Cadiro(val cadiroBuilder: CadiroBuilder[CompleteSearchQuery], val searchResult: SearchResult) extends PoeTrade {

  protected val iterResultList: Iterator[List[String]] = searchResult.result.getOrElse(List()).grouped(ApiHostConf.pageLimit)

  override def refresh: Option[Cadiro] = cadiroBuilder.execute

  def getNext: Option[CadiroObservable] = {
    CadiroLogManager.logger.info("Fetching Next Group of Items")
    if (iterResultList.hasNext) {
      val nextResultList: List[String] = iterResultList.next()
      val url = Cadiro.getFetchUrl(nextResultList, searchResult.id)

      HttpNetManager.sr(
        HttpNetManager.createGet(url),
        closeableHttpResponse => {
          CadiroObservable(
            cadiroBuilder,
            searchResult.copy(result = searchResult.result match {
              case Some(resultList) => Some(resultList.filterNot(nextResultList.contains(_)))
              case None => None
            }),
            closeableHttpResponse
          )})
    } else {
      CadiroLogManager.logger.info("No Items Left to Fetch")
      None
    }
  }

}

class CadiroObservable(
                       override val cadiroBuilder: CadiroBuilder[CompleteSearchQuery],
                       override val searchResult: SearchResult,
                       val observableList: List[FetchResult])
  extends Cadiro(cadiroBuilder, searchResult) {

  observableList.foreach(fetchResult => {
    CadiroLogManager.logger.debug("Id: {}", fetchResult.id)
    CadiroLogManager.logger.debug("Item: {}", fetchResult.item)
    CadiroLogManager.logger.debug("Whisper: {}", fetchResult.listing.get.whisper)
  })

}

object CadiroObservable {
  def apply(cadiroBuilder: CadiroBuilder[CompleteSearchQuery], searchResult: SearchResult, closeableHttpResponse: CloseableHttpResponse): CadiroObservable = {
    CadiroLogManager.logger.info("Parsing Fetch Result Json into Cadiro Models")
    new CadiroObservable(
      cadiroBuilder,
      searchResult,
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")
      ).as[FetchRootInterface].result
    )
  }
}

sealed trait SearchEntry
object Cadiro {

  def apply(cadiroBuilder: CadiroBuilder[CompleteSearchQuery], closeableHttpResponse: CloseableHttpResponse): Cadiro = {
    CadiroLogManager.logger.info("Parsing Search Result Json into Cadiro Models")
    new Cadiro(
      cadiroBuilder,
      Json.parse(
        HttpNetManager
          .getEntity(closeableHttpResponse)
          .getOrElse("")

      ).as[SearchResult]
    )
  }

  def getFetchUrl(fetchIds: List[String], searchId: String): String = ApiHostConf.fetchHost.concat("/" + fetchIds.mkString(",").concat("?query=").concat(searchId))

  def setLeague(league: String): CadiroBuilder[LeagueEntry] = {
    CadiroLogManager.logger.info("Setting League: {}", league)
    CadiroBuilder[LeagueEntry](League(league, league))
  }
  def setLeague(league: League): CadiroBuilder[LeagueEntry] = {
    CadiroLogManager.logger.info("Setting League: {}", league.id)
    CadiroBuilder[LeagueEntry](league)
  }

  sealed trait LeagueEntry extends SearchEntry
  sealed trait StatusEntry extends SearchEntry
  sealed trait SimpleFilterEntry extends SearchEntry

  type CompleteSearchQuery = LeagueEntry with StatusEntry with SimpleFilterEntry
}

case class CadiroBuilder[E <: SearchEntry](
                                            league: League,
                                            name: Option[String] = None,
                                            status: Option[Status] = None,
                                            base: Option[String] = None,
                                            order: Option[Sorting] = None,
                                            filterList: List[CadiroFilter] = List()
                                          ) {

  // mandatory
  def setStatus(status: Status): CadiroBuilder[E with Cadiro.StatusEntry] = {
    CadiroLogManager.logger.info("Setting Status Filter: {}", status)
    this.copy(status = Some(status))
  }

  // need at least one
  def searchItem(name: String): CadiroBuilder[E with Cadiro.SimpleFilterEntry] = {
    CadiroLogManager.logger.info("Setting Search Query: {}", name)
    this.copy(name = Some(name))
  }

  def setBaseItem(base: String): CadiroBuilder[E with Cadiro.SimpleFilterEntry] = {
    CadiroLogManager.logger.info("Setting Base Item Query: {}", base)
    this.copy(base = Some(base))
  }

  def setPriceOrder(order: Sorting): CadiroBuilder[E with Cadiro.SimpleFilterEntry] = {
    CadiroLogManager.logger.info("Setting Ordering Filter: {}", order)
    this.copy(order = Some(order))
  }

  def addFilter(filter: CadiroFilter): CadiroBuilder[E with Cadiro.SimpleFilterEntry] = {
    CadiroLogManager.logger.info("Adding Filter Type: {}", filter.getType)
    this.copy(filterList = filterList :+ filter)
  }


  def imprint(implicit ev: E =:= Cadiro.CompleteSearchQuery): CadiroBuilder[CompleteSearchQuery] = {
    CadiroLogManager.logger.info("Saving State of Current Builder Configs")
    this.asInstanceOf[CadiroBuilder[CompleteSearchQuery]]
  }

  def execute(implicit ev: E =:= Cadiro.CompleteSearchQuery): Option[Cadiro] = {
    CadiroLogManager.logger.info("Executing Query From Builder Configs")

    val searchQuery = SearchQuery(
      status.get.toStatusOption,
      name,
      Some(filterList.foldRight(CadiroFilter.emptyFilter)((filter, filterAcc) => filter.integrate(filterAcc))),
      base
    )
    val url = ApiHostConf.searchHost.concat("/" + HttpNetManager.encodeUrl(league.id.capitalize))
    val entity = Json.toJson(SearchQueryRoot(searchQuery, order.flatMap(_.toSortingOption))).toString()

    HttpNetManager.sr(
      HttpNetManager.createPost(url, entity),
      closeableHttpResponse => {
        Cadiro(this.imprint, closeableHttpResponse)
      }
    )
  }

}