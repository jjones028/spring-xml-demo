package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class TransactionLine(
    @field:JacksonXmlProperty(localName = "ItemLine")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val itemLines: MutableList<ItemLine> = mutableListOf(),

    @field:JacksonXmlProperty(localName = "TransactionTax")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val transactionTaxes: MutableList<TransactionTax> = mutableListOf(),

    @field:JacksonXmlProperty(localName = "TenderInfo")
    @field:JacksonXmlElementWrapper(useWrapping = false)
    val tenderInfos: MutableList<TenderInfo> = mutableListOf()
) {
    fun setItemLines(value: List<ItemLine>) {
        itemLines.addAll(value)
    }

    fun setTransactionTaxes(value: List<TransactionTax>) {
        transactionTaxes.addAll(value)
    }

    fun setTenderInfos(value: List<TenderInfo>) {
        tenderInfos.addAll(value)
    }
}
