package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class TransactionTax (
    @field:JacksonXmlProperty(localName = "TaxCollectedAmount")
    val taxCollectedAmount: Float?,

    @field:JacksonXmlProperty(localName = "TaxableSaleAmount")
    val taxableSaleAmount: Float?
)
