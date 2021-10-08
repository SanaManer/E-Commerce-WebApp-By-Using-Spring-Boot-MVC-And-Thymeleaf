

public interface ProductRepository  extends JpaRepository<Product,Long>{
	
	List<Product> findAllByCategory_id(int id);

}
