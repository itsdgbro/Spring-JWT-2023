# Spring-JWT-2023
Spring-JWT-2023

Follow up https://www.learncodewithdurgesh.com/blogs/jwt-authentication-with-spring-boot-31

<ul>
  <li>Initialize and configure maven project</li>
  <li>Dependency Web, Security, JWT</li>
</ul>

<h1>For Firing Spring Security</h1>
Authentication gets to works first to authenticate.
<li>`AuthenticationManger` is the heart of the security who is responsible for receiving credentials.</li>
<li>`ProviderManager` is responsible for managing the `AuthenticationProviders`</li>
<li>AuthManager iterates over all AuthProviders for suitable candidate to handle the received object</li>
<li>Over iterating and testing all providers, if none success then returns authentication fail</li>
<li>Authentication is done by the `username` where it is in `UserDetailsService` which has `loadUserByUserName()`</li>