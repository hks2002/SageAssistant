/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:16:06
 * @FilePath       : \web2\src\mock\services\srvInfo.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import Mock from 'mockjs'

Mock.mock(/^\/Data\/SrvInfo$/, () => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SrvInfo')
  return {
    name: 'MockData',
    version: '0.0.0',
    springBootVersion: '0.0.0'
  }
})

Mock.mock(/^\/Data\/SrvProjectDependencies$/, () => {
  console.debug('\u001b[35m' + '[Mocking] ', 'SrvProjectDependencies')
  return [
    {
      groupId: 'org.springframework.boot',
      artifactId: 'spring-boot-starter-web',
      version: '2.2.4.RELEASE',
      type: 'jar'
    },
    {
      groupId: 'org.springframework.boot',
      artifactId: 'spring-boot-starter-log4j2',
      version: '2.2.4.RELEASE',
      type: 'jar'
    },
    {
      groupId: 'org.springframework.boot',
      artifactId: 'spring-boot-starter-tomcat',
      version: '2.2.4.RELEASE',
      type: 'jar'
    },
    {
      groupId: 'org.springframework.boot',
      artifactId: 'spring-boot-starter-test',
      version: '2.2.4.RELEASE',
      type: 'jar'
    },
    {
      groupId: 'org.springframework.boot',
      artifactId: 'spring-boot-devtools',
      version: '2.2.4.RELEASE',
      type: 'jar'
    },
    {
      groupId: 'org.springframework.boot',
      artifactId: 'spring-boot-starter-jdbc',
      version: '2.2.4.RELEASE',
      type: 'jar'
    },
    {
      groupId: 'com.microsoft.sqlserver',
      artifactId: 'mssql-jdbc',
      version: '7.4.1.jre8',
      type: 'jar'
    },
    {
      groupId: 'com.github.pagehelper',
      artifactId: 'pagehelper-spring-boot-starter',
      version: '1.2.13',
      type: 'jar'
    },
    {
      groupId: 'com.google.guava',
      artifactId: 'guava',
      version: '24.1.1-jre',
      type: 'jar'
    },
    {
      groupId: 'com.alibaba',
      artifactId: 'fastjson',
      version: '1.2.66',
      type: 'jar'
    },
    {
      groupId: 'com.rometools',
      artifactId: 'rome',
      version: '1.8.0',
      type: 'jar'
    },
    {
      groupId: 'commons-collections',
      artifactId: 'commons-collections',
      version: '3.2.2',
      type: 'jar'
    },
    {
      groupId: 'commons-lang',
      artifactId: 'commons-lang',
      version: '2.1',
      type: 'jar'
    },
    {
      groupId: 'commons-logging',
      artifactId: 'commons-logging',
      version: '1.2',
      type: 'jar'
    },
    {
      groupId: 'org.apache.logging.log4j',
      artifactId: 'log4j-1.2-api',
      version: '2.12.1',
      type: 'jar'
    },
    {
      groupId: 'com.ibm.icu',
      artifactId: 'icu4j',
      version: '60.1',
      type: 'jar'
    },
    {
      groupId: 'SAP',
      artifactId: 'AzleaUflBarcode',
      version: '1.0.0',
      type: 'jar'
    },
    {
      groupId: 'SAP',
      artifactId: 'CrystalCommon2',
      version: '1.0.0',
      type: 'jar'
    },
    {
      groupId: 'SAP',
      artifactId: 'CrystalReportsRuntime',
      version: '1.0.0',
      type: 'jar'
    },
    { groupId: 'SAP', artifactId: 'cvom', version: '1.0.0', type: 'jar' },
    {
      groupId: 'SAP',
      artifactId: 'DatabaseConnectors',
      version: '1.0.0',
      type: 'jar'
    },
    { groupId: 'SAP', artifactId: 'JaiImageio', version: '1.0.0', type: 'jar' },
    {
      groupId: 'SAP',
      artifactId: 'JDBInterface',
      version: '1.0.0',
      type: 'jar'
    },
    { groupId: 'SAP', artifactId: 'jrcerom', version: '1.0.0', type: 'jar' },
    { groupId: 'SAP', artifactId: 'logging', version: '1.0.0', type: 'jar' },
    {
      groupId: 'SAP',
      artifactId: 'pfjgraphics',
      version: '1.0.0',
      type: 'jar'
    },
    {
      groupId: 'SAP',
      artifactId: 'QueryBuilder',
      version: '1.0.0',
      type: 'jar'
    },
    {
      groupId: 'SAP',
      artifactId: 'keycodeDecoder',
      version: '1.0.0',
      type: 'jar'
    },
    {
      groupId: 'commons-configuration',
      artifactId: 'commons-configuration',
      version: '1.10',
      type: 'jar'
    }
  ]
})
