package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class RegisterDetail(
    @field:JacksonXmlProperty(localName = "detailType", isAttribute = true)
    val detailType: String
)
