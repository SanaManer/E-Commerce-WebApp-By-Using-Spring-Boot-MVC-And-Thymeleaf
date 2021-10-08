
@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@GetMapping({"/", "/home"})
	public String Home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}

	@GetMapping({"/shop"})
	public String Shop(Model model) 
	{
		model.addAttribute("categories", categoryService.getAllcategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";

	}
	
	@GetMapping({"/shop/category/{id}"})
	public String ShopByCategory(Model model, @PathVariable int id ) 
	{
		model.addAttribute("categories", categoryService.getAllcategory());
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("products", productService.getAllProductById(id));
		return "shop";

	}
	
	@GetMapping({"/shop/viewproduct/{id}"})
	public String viewProduct(Model model, @PathVariable int id ) 
	{
		model.addAttribute("product",productService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";

	}


	
}
