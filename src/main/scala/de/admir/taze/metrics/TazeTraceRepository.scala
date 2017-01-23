package de.admir.taze.metrics

import java.util

import com.google.gson.Gson
import com.typesafe.scalalogging.LazyLogging
import org.springframework.boot.actuate.trace.{InMemoryTraceRepository, Trace, TraceRepository}
import org.springframework.stereotype.Component

@Component
case class TazeTraceRepository() extends TraceRepository with LazyLogging {
  val gson = new Gson()
  val inMemoryTraceRepository = new InMemoryTraceRepository()

  override def findAll(): util.List[Trace] = inMemoryTraceRepository.findAll()

  override def add(traceInfo: util.Map[String, AnyRef]): Unit = {
    logger.info(gson.toJson(traceInfo))
    inMemoryTraceRepository.add(traceInfo)
  }
}
