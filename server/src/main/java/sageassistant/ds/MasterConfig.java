package sageassistant.ds;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "sageassistant.dao", sqlSessionTemplateRef = "MasterSessionTemplate")
public class MasterConfig {

    @Bean(name = "MasterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource MasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "MasterSessionFactory")
    @Primary
    public SqlSessionFactory MasterSessionFactory(@Qualifier("MasterDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "MasterTransactionManager")
    @Primary
    public DataSourceTransactionManager MasterTransactionManager(
            @Qualifier("MasterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "MasterSessionTemplate")
    @Primary
    public SqlSessionTemplate MasterSessionTemplate(
            @Qualifier("MasterSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}