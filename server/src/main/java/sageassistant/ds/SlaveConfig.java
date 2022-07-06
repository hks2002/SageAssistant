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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "sageassistant.dao2", sqlSessionTemplateRef = "SlaveSessionTemplate")
public class SlaveConfig {

    @Bean(name = "SlaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource SlaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "SlaveSessionFactory")
    public SqlSessionFactory SlaveSessionFactory(@Qualifier("SlaveDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper2/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "SlaveTransactionManager")
    public DataSourceTransactionManager SlaveTransactionManager(
            @Qualifier("SlaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "SlaveSessionTemplate")
    public SqlSessionTemplate SlaveSessionTemplate(
            @Qualifier("SlaveSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}