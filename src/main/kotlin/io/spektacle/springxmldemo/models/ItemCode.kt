package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class ItemCode(
    @field:JacksonXmlProperty(localName = "POSCode")
    val posCode: String
)
