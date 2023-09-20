package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class TransactionDetailGroup(
    @field:JacksonXmlProperty(localName = "TransactionLine")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val transactionLines: MutableList<TransactionLine> = mutableListOf()
)
