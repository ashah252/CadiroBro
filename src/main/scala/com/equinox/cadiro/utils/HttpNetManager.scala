package com.equinox.cadiro.utils


import java.net.URLEncoder

import org.apache.http.HttpEntity
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost, HttpUriRequest}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.slf4j.{Logger, LoggerFactory}


object HttpNetManager {




  final val SUCCESS_RESPONSE_MIN: Int = 200
  final val SUCCESS_RESPONSE_MAX: Int = 299
  final val CONTENT_TYPE: String = "Content-type"
  final val JSON_CONTENT_TYPE: String = "application/json"
  final val defaultEncoding: String = "utf-8"

  def isSuccess(statusCode: Int): Boolean = {

    //https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
    // success is between 200 and 299
    statusCode >= SUCCESS_RESPONSE_MIN && statusCode <= SUCCESS_RESPONSE_MAX

  }

  def encodeUrl(url: String): String = URLEncoder.encode(url, defaultEncoding)

  def getEntity(closeableHttpResponse: CloseableHttpResponse): Option[String] = {
    closeableHttpResponse.getEntity match {
      case null => None
      case entity =>
        Some(scala.io.Source.fromInputStream(entity.getContent).mkString)
    }
  }

  def createPost(url: String, entity: String): HttpPost = {

    val httpEntity: HttpEntity = new StringEntity(entity)

    CadiroLogManager.logger.info("Creating Post Request: {}", url)
    CadiroLogManager.logger.debug("Post Request Entity: {}", scala.io.Source.fromInputStream(httpEntity.getContent).mkString)

    val httpPost: HttpPost = new HttpPost(url)
    httpPost.setEntity(httpEntity)
    httpPost.setHeader(CONTENT_TYPE, JSON_CONTENT_TYPE)
    httpPost
  }

  def createGet(url: String): HttpGet = {
    CadiroLogManager.logger.info("Creating Get Request: {}", url)
    new HttpGet(url)
  }

  def sr[A](request: HttpUriRequest, success: CloseableHttpResponse => A): Option[A] = {

    val response: CloseableHttpResponse = HttpClientBuilder.create.build.execute(request)

    response.getStatusLine.getStatusCode match {
      case statusCode if HttpNetManager.isSuccess(statusCode)  =>
        CadiroLogManager.logger.info("Received Http 200 OK")
        Some(success(response))

      case statusCode =>
        CadiroLogManager.logger.info("Didn't Receive Http 200 OK, Status = {}", statusCode)
        None
    }
  }

}
