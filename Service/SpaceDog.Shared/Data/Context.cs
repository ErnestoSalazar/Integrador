﻿using SpaceDog.Shared.Models;
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

        public DbSet<Usuario> Usuarios { get; set; }
        public DbSet<Barco>   Barcos   { get; set; }
        public DbSet<Carga>   Cargas   { get; set; }
        public DbSet<Entrada> Entradas { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
        }

    }
}
