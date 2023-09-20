package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class SaleEvent(
    @field:JacksonXmlProperty(localName = "TransactionID")
    val transactionId: Long
)
