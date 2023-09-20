package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class TenderInfo(
    @field:JacksonXmlProperty(localName = "Tender")
    val tender: Tender,

    @field:JacksonXmlProperty(localName = "TenderAmount")
    val tenderAmount: Float?
)
