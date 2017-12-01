using System;
using Microsoft.Owin.Security;
using System.Security.Claims;
using System.Threading.Tasks;
using System.Collections.Generic;
using Microsoft.Owin.Security.OAuth;
using SpaceDog.Service.Services;
using SpaceDog.Shared;

namespace SpaceDog.Service.Providers
{
    public class OAuthProvider : OAuthAuthorizationServerProvider
    {
        #region[GrantResourceOwnerCredentials]
        public override Task GrantResourceOwnerCredentials(OAuthGrantResourceOwnerCredentialsContext context)
        {
            return Task.Factory.StartNew(() =>
            {
                var userName = context.UserName;
                var password = context.Password;
                var userService = new UserService(); // our created one
                var user = userService.ValidateUser(userName, password);
                if (user != null)
                {
                    if(user.Rol != Shared.Models.Rol.Pescador)
                    {
                        if(user.IsDeleted != true)
                        {
                            var claims = new List<Claim>()
                            {
                                new Claim(ClaimTypes.Sid, Convert.ToString(user.Id)),
                                new Claim(ClaimTypes.Name, user.Nombre),
                                new Claim(ClaimTypes.Email, user.Correo),
                                new Claim(ClaimTypes.Role, user.Rol.ToString())
                            };
                            ClaimsIdentity oAuthIdentity = new ClaimsIdentity(claims,
                                        Startup.OAuthOptions.AuthenticationType);

                            var properties = CreateProperties(user.Correo, user.Rol.ToString(), user.Id.ToString());
                            var ticket = new AuthenticationTicket(oAuthIdentity, properties);
                            context.Validated(ticket);
                        }
                        else
                        {
                            context.SetError("invalid_grant", Strings.USUARIO_ELIMINADO);
                        }
                    }
                    else
                    {
                        context.SetError("invalid_grant", Strings.NO_AUTORIZADO);
                    }
                }
                else
                {
                    context.SetError("invalid_grant", Strings.LOGIN_INVALIDO);
                }
            });
        }
        #endregion

        #region[ValidateClientAuthentication]
        public override Task ValidateClientAuthentication(OAuthValidateClientAuthenticationContext context)
        {
            if (context.ClientId == null)
                context.Validated();

            return Task.FromResult<object>(null);
        }
        #endregion

        #region[TokenEndpoint]
        public override Task TokenEndpoint(OAuthTokenEndpointContext context)
        {
            foreach (KeyValuePair<string, string> property in context.Properties.Dictionary)
            {
                context.AdditionalResponseParameters.Add(property.Key, property.Value);
            }

            return Task.FromResult<object>(null);
        }
        #endregion

        #region[CreateProperties]
        public static AuthenticationProperties CreateProperties(string userName, string rol, string id)
        {
            IDictionary<string, string> data = new Dictionary<string, string>
            {
                { "userName", userName },
                { "rol", rol },
                { "userId", id }
            };
            return new AuthenticationProperties(data);
        }
        #endregion
    }
}