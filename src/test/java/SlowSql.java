import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.util.JdbcConstants;

import java.util.Objects;

/**
 * Created by lixiliang on 2019/1/10.
 */
public class SlowSql {
    private String sql;
    private String host;
    private String db;
    private String table;
    private double maxQueryTime;
    private int maxExaminedRow;
    private int count;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public double getMaxQueryTime() {
        return maxQueryTime;
    }

    public void setMaxQueryTime(double maxQueryTime) {
        this.maxQueryTime = maxQueryTime;
    }

    public int getMaxExaminedRow() {
        return maxExaminedRow;
    }

    public void setMaxExaminedRow(int maxExaminedRow) {
        this.maxExaminedRow = maxExaminedRow;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        SlowSql slowSql = (SlowSql) o;
        return Objects.equals(ParameterizedOutputVisitorUtils.parameterize(sql,JdbcConstants.MYSQL),ParameterizedOutputVisitorUtils.parameterize(slowSql.sql,JdbcConstants.MYSQL)) && Objects.equals(db, slowSql.db);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sql, db);
    }

    @Override
    public String toString() {
        return "SlowSql{" + "sql='" + sql + '\'' + ", host='" + host + '\'' + ", db='" + db + '\'' + ", table='" + table + '\'' + ", maxQueryTime=" + maxQueryTime + ", maxExaminedRow=" + maxExaminedRow + ", count=" + count + '}';
    }
}
