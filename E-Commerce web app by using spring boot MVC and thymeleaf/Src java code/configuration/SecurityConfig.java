
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
       http
               .authorizeRequests()
               .antMatchers("/","/shop/**","/register").permitAll()
               .antMatchers("/admin/**").hasRole("ADMIN")
               .anyRequest()
               .authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .permitAll()
               .failureUrl("/login?error= true")
               .defaultSuccessUrl("/")
               .usernameParameter("email")
               .passwordParameter("password")
               .and()
               .oauth2Login()
               .loginPage("/login")
               .successHandler(googleOAuth2SuccessHandler)
               .and()
               .logout()
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
               .logoutSuccessUrl("/login")
               .invalidateHttpSession(true)
               .deleteCookies("JSESSIONID")
               .and()
               .exceptionHandling()
               .and()
               .csrf()
               .disable();

       http.headers().frameOptions().disable();

       
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserDetailService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/resources/**","/static/**","/images/**","/productImages/**","/css/**","/js/**");
    }
}
