using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
{

    public class EntradaDto
    {

        public int Id { get; set; }
        public string Folio { get; set; }
        public DateTime Fecha { get; set; }
        public DateTime Hora { get; set; }
        public Turno Turno { get; set; }

        public int UsuarioId { get; set; }
        public Usuario Usuario { get; set; }

        public List<int> CargasId { get; set; }


        public Entrada ToModel()
        {
            return new Entrada()
            {
                Folio   =  Folio,
                Fecha   =  DateTime.Now,
                Hora    =  DateTime.Now,
                Turno   =  Turno,
                Usuario =  Usuario
            };
        }

    }
}