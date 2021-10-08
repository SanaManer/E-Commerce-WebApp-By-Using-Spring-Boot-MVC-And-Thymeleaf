
@Service
public class CustomUserDetailService implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        Optional<User> user= userRepository.findUserByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("User not Found"));
        return user.map(CustomUserDetail::new).get();

    }
}
