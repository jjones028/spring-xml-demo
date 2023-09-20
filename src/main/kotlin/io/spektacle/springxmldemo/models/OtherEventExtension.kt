package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class OtherEventExtension(
    @field:JacksonXmlProperty(localName = "ShiftNumber")
    val shiftNumber: Long
)
