### 基于 jdbc 的用户认证



**从数据库 mysql 中获取用户的身份信息（用户名称，密码，角色）**

**在 spring security 框架对象用户信息的表示类是 UserDetails. UserDetails **

**是一个接口，高度抽象的用户信息类（相当于项目中的 User 类）**



**UserDetails接口**

```java
package org.springframework.security.core.userdetails;

import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public interface UserDetails extends Serializable {
    //角色，权限集合
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();
	
    //是否过期
    boolean isAccountNonExpired();

    //是否锁定
    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    //是否启用
    boolean isEnabled();
}
```



**User 类：是 UserDetails 接口的实现类， 构造方法有三个参数： username，password, authorities**

```java
public class User implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = 530L;
    private static final Log logger = LogFactory.getLog(User.class);
    private String password;
    private final String username;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }
```



**需要向 spring security 提供 User 对象， 这个对象的数据来自数据库 的查询**



**需要实现 UserDetailsService 接口**

```java
package org.springframework.security.core.userdetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException;
}
```



**重写方法 UserDetails loadUserByUsername(String var1) **

**在方法中获取数据库中的用户信息， 也就是执行数据库的查询，条件是用户名称。**