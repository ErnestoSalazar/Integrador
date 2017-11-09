using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    class DatabaseInitializer : DropCreateDatabaseIfModelChanges<Context>
    {

        protected override void Seed(Context context)
        {
            //var user = new User()
            //{
            //    Name = "",
            //    Email = "",
            //    Password = "",
            //    Role = ""
            //};

            //context.Users.Add(user);
            //context.SaveChanges();
        }

    }
}
