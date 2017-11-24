using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class Context : DbContext
    {
        // @"{connection string}"
        public Context() : base(Strings.CONNECTION_STRING)
        {
            Database.SetInitializer(new DatabaseInitializer());
            this.Configuration.LazyLoadingEnabled = true;
        }

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Barco>   Barcos   { get; set; }
        public DbSet<Carga>   Cargas   { get; set; }
        public DbSet<Entrada> Entradas { get; set; }


        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Conventions.Remove<OneToManyCascadeDeleteConvention>();
        }

    }
}
