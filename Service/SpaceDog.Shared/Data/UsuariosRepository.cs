using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class UsuariosRepository : BaseRepository<Usuario>
    {

        public UsuariosRepository(Context context) : base(context)
        {
        }

        public override Usuario Get(int id, bool includeRelatedEntities = true)
        {
            var user = Context.Usuarios.AsQueryable();

            return user
                .Where(u => u.Id == id && u.IsDeleted != true)
                .SingleOrDefault();
            
        }

        public override IList<Usuario> GetList()
        {
            return Context.Usuarios
                .OrderBy(u => u.Nombre)
                .Where(u => u.IsDeleted != true)
                .ToList();
        }

        public IList<Usuario> GetUsuariosByName(string nombre, string apellido)
        {
            if (nombre != null && nombre.Length > 0 && apellido != null && apellido.Length > 0)
            {
                return Context.Usuarios
                .OrderBy(u => u.Nombre)
                .Where(u => u.Nombre.ToLower().Contains(nombre.ToLower()) && u.Apellido.ToLower().Contains(apellido.ToLower()) && u.IsDeleted != true)
                .ToList();
            }
            else if (nombre != null && nombre.Length > 0)
            {
                return Context.Usuarios
                .OrderBy(u => u.Nombre)
                .Where(u => u.Nombre.ToLower().Contains(nombre.ToLower()) && u.IsDeleted != true)
                .ToList();
            }
            else if (apellido != null && apellido.Length > 0)
            {
                return Context.Usuarios
                .OrderBy(u => u.Nombre)
                .Where(u => u.Apellido.ToLower().Contains(apellido.ToLower()) && u.IsDeleted != true)
                .ToList();
            }
            else
            {
                return null;
            }
            
        }

        public IList<Usuario> GetUsersByRol(string rol)
        {
            return Context.Usuarios
                .OrderBy(u => u.Nombre)
                .Where(u => u.IsDeleted != true && u.Rol.ToString() == rol)
                .ToList();
        }

        public void DeleteD(int id)
        {
            var user = Context.Usuarios.Find(id);
            user.IsDeleted = true;
            Context.Entry(user).State = System.Data.Entity.EntityState.Modified;
            Context.SaveChanges();
        }

        public Usuario ValidateUser(string name, string password)
        {
            var user = Context.Usuarios.AsQueryable();

            return user.FirstOrDefault(u => u.Nombre == name && u.Password == password);
        }



    }
}
