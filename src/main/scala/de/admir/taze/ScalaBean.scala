package de.admir.taze

import javax.annotation.PostConstruct

import de.admir.taze.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import collection.JavaConverters._


@Component
class ScalaBean {
  @Autowired
  var productRepository: ProductRepository = _

  @PostConstruct
  def printProducts(): Unit = {
    val products = productRepository.findAll().asScala.toList
    products.foreach(println)
    println(products.getClass)
  }
}
