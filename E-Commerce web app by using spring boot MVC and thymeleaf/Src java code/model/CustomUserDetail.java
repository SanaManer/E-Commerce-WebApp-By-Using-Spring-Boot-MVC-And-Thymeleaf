

public class CustomUserDetail  extends User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomUserDetail(User user)
	{
		super(user);
	}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        List<GrantedAuthority> authorityList= new ArrayList<>();
        super.getRoles().forEach(role ->
        {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));

        });
        return authorityList;

    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
