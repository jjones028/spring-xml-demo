package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.time.LocalDate

data class TransmissionHeaderExtension(
    @field:JacksonXmlProperty(localName = "StoreName")
    val storeName: String,

    @field:JacksonXmlProperty(localName = "BusinessDate")
    val businessDate: LocalDate
)
