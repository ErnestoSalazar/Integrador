using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using SpaceDog.Shared.Dto;
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
                .Where(e => e.Id == id && e.IsDeleted != true)
                .SingleOrDefault();
        }

        public override IList<Entrada> GetList()
        {
            return Context.Entradas
                .Include(e => e.Usuario)
                .Include(e => e.Cargas)
                .Where(e => e.IsDeleted != true)
                .ToList();
        }

        public void DeleteD(int id)
        {
            var entrada = Context.Entradas.Find(id);
            entrada.IsDeleted = true;
            Context.Entry(entrada).State = EntityState.Modified;
            Context.SaveChanges();
        }

        public dynamic GetListByDate(DateTime dateInicio, DateTime dateFin, string especie)
        {

            var entradas = Context.Entradas
                .Select(en => new EntradaDto
                {
                    Id = en.Id,
                    Folio = en.Folio,
                    Fecha = en.Fecha,
                    Hora = en.Hora,
                    Turno = en.Turno,
                    UsuarioId = en.UsuarioId,
                    Cargas = en.Cargas.Where(c => c.Especie.ToString() == especie).ToList(),
                    IsDeleted = en.IsDeleted
                })
                .Where(e => e.Fecha >= dateInicio && e.Fecha <= dateFin && e.Cargas.Any(c => c.Especie.ToString() == especie) && e.IsDeleted != true)
                .ToList();

            var totalPesaje = 0.0;

            foreach(var entrada in entradas)
            {
                totalPesaje += entrada.Cargas.Sum(c=> c.Cantidad);
                entrada.TotalPesaje = totalPesaje;
                totalPesaje = 0.0;
            }
            return entradas;
        }

        /*
         

            SELECT * ,
            (SELECT SUM(cantidad) FROM Cargas c RIGHT JOIN Entradas e
            ON c.Entrada_Id = 1 
            WHERE c.Especie = 1 AND e.Fecha BETWEEN 
            CONVERT(datetime,'14/11/2017 12:00:00 am',103)
            AND 
            CONVERT(datetime,'14/11/2017 12:00:00 am',103)) as total
            FROM Cargas c INNER JOIN Entradas e
            ON c.Entrada_Id = 1 
            WHERE c.Especie = 1

         */
        public List<double> GetTotalDePesoDePescados(DateTime dateInicio, DateTime dateFin)
        {
            var sql = Context.Database.SqlQuery<double>(
                "SELECT SUM(cantidad) FROM Cargas c INNER JOIN Entradas e " +
                "ON c.Entrada_Id = 1 " +
                "WHERE c.Especie = 1 AND e.Fecha BETWEEN  CONVERT(datetime,'"+dateInicio+"',103)  AND CONVERT(datetime,'"+dateFin+"',103) ;").ToList();
            return sql;

            
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
