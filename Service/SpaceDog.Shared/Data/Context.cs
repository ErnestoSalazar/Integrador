using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class Context : DbContext
    {
        // @"{connection string}"
        public Context() : base(@"")
        {
            Database.SetInitializer(new DatabaseInitializer());
        }

        public DbSet<User> Users { get; set; }
    }
}
