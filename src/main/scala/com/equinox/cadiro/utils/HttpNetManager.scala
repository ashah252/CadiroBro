package com.equinox.cadiro.utils


import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost, HttpUriRequest}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.slf4j.{Logger, LoggerFactory}


object HttpNetManager {

  final val OK_RESPONSE: Int = 200
  final val logger: Logger = LoggerFactory.getLogger(HttpNetManager.getClass)

  def getEntity(closeableHttpResponse: CloseableHttpResponse): Option[String] = {
    closeableHttpResponse.getEntity match {
      case null => None
      case entity =>
        Some(scala.io.Source.fromInputStream(entity.getContent).mkString)
    }
  }

  def createPost(url: String, entity: StringEntity): HttpPost = {
    val httpPost: HttpPost = new HttpPost(url)
    httpPost.setEntity(entity)
    httpPost
  }

  def createGet(url: String): HttpGet = {
    new HttpGet(url)
  }

  def sr[A](request: HttpUriRequest, success: CloseableHttpResponse => A): Option[A] = {

    val response: CloseableHttpResponse = HttpClientBuilder.create.build.execute(request)

    response.getStatusLine.getStatusCode match {
      case OK_RESPONSE =>
        logger.info("Received Http 200 OK")
        Some(success(response))

      case statusCode =>
        logger.info("Didnt Receive Http 200 OK, Status = {}", statusCode)
        None
    }
  }

}
