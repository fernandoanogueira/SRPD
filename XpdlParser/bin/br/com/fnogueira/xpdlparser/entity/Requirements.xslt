<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
  <html>
  <body>
  
    <xsl:for-each select="RequirementSpecification/Metadata">
      <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
        <tr>
          <th style="text-align:left" bgcolor="99ccff">XPDL file</th>
          <td style="text-align:left"><xsl:value-of select="source"/></td>
        </tr>
        <tr>
          <th style="text-align:left" bgcolor="99ccff">XPDL version</th>
          <td style="text-align:left"><xsl:value-of select="xpdlVersion"/></td>
        </tr>
        <tr>
          <th style="text-align:left" bgcolor="99ccff">BPMN modeling tool</th>
          <td style="text-align:left"><xsl:value-of select="vendor"/></td>
        </tr>
        <tr>
          <th style="text-align:left" bgcolor="99ccff">Author</th>
          <td style="text-align:left"><xsl:value-of select="author"/></td>
        </tr>
        <tr>
          <th style="text-align:left" bgcolor="99ccff">BPMN model creation</th>
          <td style="text-align:left"><xsl:value-of select="dateOfCreation"/></td>
        </tr>
        <tr>
          <th style="text-align:left" bgcolor="99ccff">BPMN model modification</th>
          <td style="text-align:left"><xsl:value-of select="dateOfModification"/></td>
        </tr>
        <tr>
          <th style="text-align:left" bgcolor="99ccff">Document generated</th>
          <td style="text-align:left"><xsl:value-of select="dateOfGeneration"/></td>
        </tr>
        <tr>
          <th style="text-align:left" bgcolor="99ccff">Process definition</th>
          <td style="text-align:left"><xsl:value-of select="processDescription"/></td>
        </tr>   
        <tr>
          <th style="text-align:left" bgcolor="99ccff">Process documentation</th>
          <td style="text-align:left"><xsl:value-of select="processDocumentation"/></td>
        </tr>                                           
      </table>
    </xsl:for-each>  

  <br/>
  <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
    <tr bgcolor="yellow" >
      <th style="text-align:center" colspan="3">1. Actors</th>
    </tr>
    <tr bgcolor="99ccff">
      <th style="text-align:left">id</th>
      <th style="text-align:left">name</th>
      <th style="text-align:left">description</th>
    </tr>
    <xsl:for-each select="RequirementSpecification/Actors/Actor" width="90%">
    <tr>
      <td><xsl:value-of select="id"/></td>
      <td><xsl:value-of select="name"/></td>
      <td><xsl:value-of select="description"/></td>
    </tr>
    </xsl:for-each>
  </table>
    
  <br/>
  <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
    <tr bgcolor="yellow" >
      <th style="text-align:center" colspan="3">2. Pre Conditions</th>
    </tr>  
    <tr bgcolor="99ccff">
      <th style="text-align:left">id</th>
      <th style="text-align:left">description</th>
    </tr>
    <xsl:for-each select="RequirementSpecification/PreConditions/PreCondition">
    <tr>
      <td><xsl:value-of select="id"/></td>
      <td><xsl:value-of select="description"/></td>
    </tr>
    </xsl:for-each>
  </table>  

  <br/>
  <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
    <tr bgcolor="yellow" >
      <th style="text-align:center" colspan="3">3. Post Conditions</th>
    </tr>   
    <tr bgcolor="99ccff">
      <th style="text-align:left">id</th>
      <th style="text-align:left">description</th>
    </tr>
    <xsl:for-each select="RequirementSpecification/PostConditions/PostCondition">
    <tr>
      <td><xsl:value-of select="id"/></td>
      <td><xsl:value-of select="description"/></td>
    </tr>
    </xsl:for-each>
  </table>       
      
  <br/>
  <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
    <tr bgcolor="yellow" >
      <th style="text-align:center" colspan="3">4. Functional Requirements</th>
    </tr>  
    <tr bgcolor="99ccff">
      <th style="text-align:left">id</th>
      <th style="text-align:left">description</th>
    </tr>
    <xsl:for-each select="RequirementSpecification/FunctionalRequirements/FunctionalRequirement">
    <tr>
      <td><xsl:value-of select="id"/></td>
      <td><xsl:value-of select="description"/></td>
    </tr>
    </xsl:for-each>
  </table>

  <br/>
  <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
    <tr bgcolor="yellow" >
      <th style="text-align:center" colspan="3">5. Non Functional Requirements</th>
    </tr>    
    <tr bgcolor="99ccff">
      <th style="text-align:left">id</th>
      <th style="text-align:left">description</th>
    </tr>
    <xsl:for-each select="RequirementSpecification/NonFunctionalRequirements/NonFunctionalRequirement">
    <tr>
      <td><xsl:value-of select="id"/></td>
      <td><xsl:value-of select="description"/></td>
    </tr>
    </xsl:for-each>
  </table>    
    
  <br/>
  <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
    <tr bgcolor="yellow" >
      <th colspan="3">6. Business Rules</th>
    </tr>  
    <tr bgcolor="99ccff">
      <th style="text-align:left">id</th>
      <th style="text-align:left">description</th>
    </tr>
    <xsl:for-each select="RequirementSpecification/BusinessRules/BusinessRule">
    <tr>
      <td><xsl:value-of select="id"/></td>
      <td><xsl:value-of select="description"/></td>
    </tr>
    </xsl:for-each>
  </table>
       
  <br/>
  <table border="1" bordercolor="white" cellpadding="0" cellspacing="0" width="90%">
    <tr bgcolor="yellow" >
      <th colspan="3">7. UML Diagrams</th>
    </tr>   
    <tr bgcolor="99ccff">
      <th>Use cases</th>
      <th>Domain classes</th>
      <th>Activity</th>
    </tr>
    <tr>
      <td style="text-align:center">
        <xsl:for-each select="RequirementSpecification/UseCaseDiagram">
          <img>
            <xsl:attribute name="src">
              <xsl:value-of select="image"/>
            </xsl:attribute>
          </img>  
        </xsl:for-each>       
      </td>
      <td style="text-align:center">
        <xsl:for-each select="RequirementSpecification/ClassDiagram">
          <img>
            <xsl:attribute name="src">
              <xsl:value-of select="image"/>
            </xsl:attribute>
          </img>  
        </xsl:for-each>       
      </td>
      <td style="text-align:center">
        <xsl:for-each select="RequirementSpecification/ActivityDiagram">
          <img>
            <xsl:attribute name="src">
              <xsl:value-of select="image"/>
            </xsl:attribute>
          </img>  
        </xsl:for-each>       
      </td>      
    </tr>
  </table>    

    
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>

