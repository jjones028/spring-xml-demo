package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Tender(
    @field:JacksonXmlProperty(localName = "TenderCode")
    val tenderCode: Long,

    @field:JacksonXmlProperty(localName = "TenderSubCode")
    val tenderSubCode: Long
)
