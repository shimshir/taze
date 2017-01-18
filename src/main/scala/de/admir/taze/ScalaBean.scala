package de.admir.taze

import javax.annotation.PostConstruct

import de.admir.taze.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
case class ScalaBean @Autowired()(productRepository: ProductRepository) {

  @PostConstruct
  def printProducts(): Unit = {
    println(s"${ScalaBean.getClass.getCanonicalName} is alive!!!")
  }
}
