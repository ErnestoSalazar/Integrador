using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceDog.Shared.Data
{
    public class EntradasRepository : BaseRepository<Entrada>
    {
        public EntradasRepository(Context context) : base(context)
        {
        }

        public override Entrada Get(int id, bool includeRelatedEntities = true)
        {
            var entrada = Context.Entradas.AsQueryable();
            if (includeRelatedEntities)
            {
                entrada = entrada.Include(e => e.Usuario)
                    .Include(e => e.Cargas);
            }

            return entrada
                .Where(e => e.Id == id)
                .SingleOrDefault();
        }

        public override IList<Entrada> GetList()
        {
            return Context.Entradas
                .Include(e => e.Usuario)
                .Include(e => e.Cargas)
                .ToList();
        }

        public List<Carga> GetListOfCargasInEntrada(ICollection<Carga> cargas)
        {
            List<Carga> _cargas = new List<Carga>();
            foreach(var carga in cargas)
            {
                Context.Cargas.Attach(carga);
                _cargas.Add(carga);
            }

            return _cargas.ToList();

        }

        public List<Carga> GetListOfCargasInEntrada(List<int> CargasId)
        {
            List<Carga> _cargas = new List<Carga>();
            Carga carga;
            foreach(var cargaId in CargasId)
            {
                carga = Context.Cargas.Find(cargaId);
                _cargas.Add(carga);
            }
            return _cargas;
        }

        public void InsertListInContext(Entrada entrada)
        {
            List<Carga> cargas = new List<Carga>();
            foreach(var carga in entrada.Cargas)
            {
                Context.Cargas.Attach(carga);
                Context.Entry(carga).State = EntityState.Modified;
                cargas.Add(carga);
            }
            entrada.Cargas = cargas;
            Context.Entradas.Add(entrada);
        }


        



    }
}
