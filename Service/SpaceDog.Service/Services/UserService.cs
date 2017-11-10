using SpaceDog.Shared.Data;
using SpaceDog.Shared.Models;
using SpaceDog.Shared.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Services
{
    public class UserService
    {
        public UserService()
        {

        }

        public User ValidateUser(string name, string password)
        {
            var _password = PasswordEncryptService.passwordEncrypt(password);
            using (var context = new Context())
            {
                User user = context.Users.FirstOrDefault(u => u.Nombre == name && u.Password == _password);
                return user;
            }
        }

        /*
         * return User if exists
         */
        public static User ValidateEmail(string mail)
        {
            using (var context = new Context())
            {
                return context.Users.FirstOrDefault(u => u.Correo == mail);
            }
        }

        public static bool OtherUserHaveSameEmail(int id, string mail)
        {
            using (var context = new Context())
            {
                return context.Users.Any(u => u.Id != id && u.Correo == mail);
            }
        }

    }
}