using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
{

    public class EntradaDto
    {

        public int Id { get; set; }
        [Required]
        public string Folio { get; set; }
        [Required]
        public DateTime Fecha { get; set; }
        [Required]
        public DateTime Hora { get; set; }
        [Required]
        public Turno? Turno { get; set; }

        public int UsuarioId { get; set; }
        public Usuario Usuario { get; set; }
        public ICollection<Carga> Cargas { get; set; }



        public List<int> CargasId { get; set; }


        public Entrada ToModel()
        {
            return new Entrada()
            {
                Id = Id,
                Folio   =  Folio,
                Fecha   =  DateTime.Now,
                Hora    =  DateTime.Now,
                Turno   =  Turno.Value,
                Usuario =  Usuario,
                Cargas = Cargas
            };
        }

    }
}