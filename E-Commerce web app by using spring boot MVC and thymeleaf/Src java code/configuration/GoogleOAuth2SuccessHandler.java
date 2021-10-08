
@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    private RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        OAuth2AuthenticationToken token=(OAuth2AuthenticationToken)  authentication;
        String email=token.getPrincipal().getAttributes().get("email").toString();
        if(userRepository.findUserByEmail(email).isPresent())
        {

        }
        else
        {
            User user=new User();
            user.setFirstName(token.getPrincipal().getAttributes().get("give_name").toString());
            user.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
            user.setEmail(email);
            List<Role> roles= new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
            user.setRoles(roles);
            userRepository.save(user);
        }
        redirectStrategy.sendRedirect(request, response,"/");

    }
}
