
import com.utp.modelo.CategoriaProducto;
import com.utp.modelo.CategoriaProductoDAOImpl;
import java.sql.SQLException;

public class prueba01 {
    public static void main(String[] args) 
            throws SQLException {
        CategoriaProducto ej=  new CategoriaProducto();
        ej.setNombre("Lacteos");
        new CategoriaProductoDAOImpl().insertar(ej);
        
        
    }
    
    
            
    
}
