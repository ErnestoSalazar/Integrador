using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class UsersRepository : BaseRepository<User>
    {

        public UsersRepository(Context context) : base(context)
        {
        }

        public override User Get(int id, bool includeRelatedEntities = true)
        {
            var user = Context.Users.AsQueryable();

            return user
                .Where(u => u.Id == id)
                .SingleOrDefault();
        }

        public override IList<User> GetList()
        {
            return Context.Users
                .OrderBy(u => u.Name)
                .ToList();
        }

        public User ValidateUser(string name, string password)
        {
            var user = Context.Users.AsQueryable();

            return user.FirstOrDefault(u => u.Name == name && u.Password == password);
        }



    }
}
