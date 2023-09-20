package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class ItemLine(
    @field:JacksonXmlProperty(localName = "ItemCode")
    val itemCode: ItemCode,

    @field:JacksonXmlProperty(localName = "Description")
    val description: String?,

    @field:JacksonXmlProperty(localName = "SalesQuantity")
    val salesQuantity: Float?,

    @field:JacksonXmlProperty(localName = "SalesAmount")
    val salesAmount: Float?
)
