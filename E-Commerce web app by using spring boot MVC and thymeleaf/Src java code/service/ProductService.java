
@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	 public List<Product> getAllProduct()
	    {
	        return productRepository.findAll()  ;
	    }
	 public void addProduct(Product product)
	 {
		 productRepository.save(product);
	 }
	 
	 
	 public void removeProductById(long id)
	 {
		 productRepository.deleteById(id);
	 }
	 
	 public Optional<Product> getProductById(long id)
	 {
		return productRepository.findById(id);
		 
	 }
	 public List<Product> getAllProductById(int id)
	 {
		return productRepository.findAllByCategory_id(id);
		 
	 }

}
