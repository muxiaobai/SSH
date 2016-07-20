package Entity;
import java.util.EnumSet;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
public class SchemaTest {
	public static void main(String[] args) {
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().configure().build(); 
		Metadata metadata=new MetadataSources(serviceRegistry).buildMetadata(); 
		SchemaExport export=new SchemaExport(); 
		export.create(EnumSet.of(TargetType.DATABASE), metadata); 
	}
}
