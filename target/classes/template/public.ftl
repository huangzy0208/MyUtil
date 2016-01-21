<#function translateStr str>
	<#if str?is_number>
		<#assign tmp=(str?c)>
	<#else>
		<#assign tmp=str>
	</#if>
  <#assign rtnValue='null'>
  <#if tmp!=''>
  	<#assign rtnValue='"'+tmp+'"'>
  </#if>
  <#return rtnValue>
</#function>
<#function translateNum str>
	<#if str?is_number>
		<#assign tmp=(str?c)>
	<#else>
		<#assign tmp=str>
	</#if>
  <#assign rtnValue='null'>
  <#if tmp!=''>
  	<#assign rtnValue=tmp>
  </#if>
	<#return rtnValue>
</#function>
<#function translateDate str srcFormat targetFarmat>
  <#assign rtnValue=''>
  <#if (str!='')&&(str?length=srcFormat?length)>
  	<#assign rtnValue=(str?date(srcFormat)?string(targetFarmat))>
  </#if>
  <#return rtnValue>
</#function>
<#function translateDateNull str srcFormat targetFarmat>
  <#assign rtnValue='null'>
  <#if (str!='')&&(str?length=srcFormat?length)>
  	<#assign rtnValue=(str?date(srcFormat)?string(targetFarmat))>
  </#if>
  <#return rtnValue>
</#function>
<#function translateDateStrNull str srcFormat targetFarmat>
  <#assign rtnValue='null'>
  <#if (str!='')&&(str?length=srcFormat?length)>
  	<#assign rtnValue='"'+(str?date(srcFormat)?string(targetFarmat))+'"'>
  </#if>
  <#return rtnValue>
</#function>
