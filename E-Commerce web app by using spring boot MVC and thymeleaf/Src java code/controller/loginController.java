

@Controller
public class loginController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String login()
    {
        GlobalData.cart.clear();
        return "login";
    }
    @GetMapping("/register")
    public String registerGet()
    {
        return "register";
    }

    @PostMapping("register")
    public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException
    {
        String password=user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password)); //Google
        List<Role> roles= new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(),password);
        return "redirect:/";

    }
}
