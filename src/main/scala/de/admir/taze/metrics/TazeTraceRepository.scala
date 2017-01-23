package de.admir.taze.metrics

import java.util

import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.trace.{InMemoryTraceRepository, Trace, TraceRepository}
import org.springframework.stereotype.Component

@Component
case class TazeTraceRepository() extends TraceRepository {
  private val logger = LoggerFactory.getLogger(TazeTraceRepository.getClass)
  private val gson = new Gson()
  private val inMemoryTraceRepository = new InMemoryTraceRepository()

  override def findAll(): util.List[Trace] = inMemoryTraceRepository.findAll()

  override def add(traceInfo: util.Map[String, AnyRef]): Unit = {
    logger.info(gson.toJson(traceInfo))
    inMemoryTraceRepository.add(traceInfo)
  }
}
