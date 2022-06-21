package com.example.finalsapplication

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name="response")
data class responseInfo(
    @Element
    val header : Header,
    @Element
    val body : myBody,
)
@Xml(name="header")
data class Header(
    @PropertyElement
    val resultCode : Int,
    @PropertyElement
    val resultMsg : String
)
@Xml(name="body")
data class myBody(
    @PropertyElement
    val numOfRows: Int,
    @PropertyElement
    val pageNo : Int,
    @PropertyElement
    val totalCount : Int,
    @Element
    val items : myItems
)
@Xml(name="items")
data class myItems(
    @Element(name="item")
    val item : MutableList<myItem>
)
@Xml
data class myItem(
    @PropertyElement
    val DESC_KOR : String?,
    @PropertyElement
    val SERVING_WT : String?,
    @PropertyElement
    val NUTR_CONT1 : String?,
    @PropertyElement
    val NUTR_CONT2 : String?,
    @PropertyElement
    val NUTR_CONT3 : String?,
    @PropertyElement
    val NUTR_CONT4 : String?,
    @PropertyElement
    val NUTR_CONT5 : String?,
    @PropertyElement
    val NUTR_CONT6 : String?,
){
    constructor() : this(null, null, null, null,null,null,null,null)
}