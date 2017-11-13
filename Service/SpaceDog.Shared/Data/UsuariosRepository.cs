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

            var _user = user
                .Where(u => u.Id == id)
                .SingleOrDefault();

            return new Usuario()
            {
                Nombre = _user.Nombre,
                Apellido = _user.Apellido,
                Rfc = _user.Rfc,
                Rol = _user.Rol
            };

        }

        public override IList<Usuario> GetList()
        {
            return Context.Usuarios
                .OrderBy(u => u.Nombre)
                .ToList();
        }

        public Usuario ValidateUser(string name, string password)
        {
            var user = Context.Usuarios.AsQueryable();

            return user.FirstOrDefault(u => u.Nombre == name && u.Password == password);
        }



    }
}
