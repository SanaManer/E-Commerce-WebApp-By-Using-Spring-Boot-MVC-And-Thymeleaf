
@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository ;

    public List<Category> getAllcategory()
    {
        return categoryRepository.findAll();
    }
    public void addCategory(Category category)
    {
        categoryRepository.save(category);
    }
    public void removeCategoryById(int id)
    {
    categoryRepository.deleteById(id);
    }
    public Optional<Category> getCategoryById(int id)
    {
		return categoryRepository.findById(id);
    	
    }
}
