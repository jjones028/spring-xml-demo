package io.spektacle.springxmldemo.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.time.LocalDate
import java.time.LocalTime

data class SaleEvent(
    @field:JacksonXmlProperty(localName = "TransactionID")
    val transactionId: Long,

    @field:JacksonXmlProperty(localName = "ReceiptDate")
    val receiptDate: LocalDate,

    @field:JacksonXmlProperty(localName = "ReceiptTime")
    val receiptTime: LocalTime,

    @field:JacksonXmlProperty(localName = "SaleEventExtension")
    val saleEventExtension: SaleEventExtension,

    @field:JacksonXmlProperty(localName = "CashierID")
    val cashierId: Long,

    @field:JacksonXmlProperty(localName = "RegisterID")
    val registerId: Long,

    @field:JacksonXmlProperty(localName = "TransactionDetailGroup")
    val transactionDetailGroup: TransactionDetailGroup
)
