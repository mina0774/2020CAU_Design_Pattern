HolubSQL.jar
Version 1.0
Released 27 Sept., 2004

/*  (c) 2004 Allen I. Holub. All rights reserved.
 *
 *  This code may be used freely by yourself with the following
 *  restrictions:
 *
 *  o Your splash screen, about box, or equivalent, must include
 *    Allen Holub's name, copyright, and URL. For example:
 *
 *      This program contains Allen Holub's SQL package.<br>
 *      (c) 2005 Allen I. Holub. All Rights Reserved.<br>
 *              http://www.holub.com<br>
 *
 *    If your program does not run interactively, then the foregoing
 *    notice must appear in your documentation.
 *
 *  o You may not redistribute (or mirror) the source code.
 *
 *  o You must report any bugs that you find to me. Use the form at
 *    http://www.holub.com/company/contact.html or send email to
 *    allen@Holub.com.
 *
 *  o The software is supplied <em>as is</em>. Neither Allen Holub nor
 *    Holub Associates are responsible for any bugs (or any problems
 *    caused by bugs, including lost productivity or data)
 *    in any of this code.
 */

< 코드 변경 사항 > 

- Exporter, Importer
com.holub.database 경로 밑에 저장 >> 
HTMLExporter 
XMLExporter 
XMLImporter

- Junit Test Code
com.holub.database 경로 밑에 저장 >> 
 JunitTest_HTMLExporter 
 JunitTest_XMLExporter 
 JunitText_XMLImporter 
 
 - SQL select * error handling 
 com.holub.database 경로 밑>> 
 ConcreteTable Class의 select 함수 수정 