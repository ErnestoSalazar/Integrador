using SpaceDog.Shared.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace SpaceDog.Service.Dto
{
    public class BarcoDto
    {
        public int Id { get; set; }
        [Required]
        public string Nombre { get; set; }
        [Required]
        public string Descripcion { get; set; }
        public int UsuarioId { get; set; }



        public Barco ToModel()
        {
            return new Barco()
            {
                Id = Id,
                Nombre = Nombre,
                Descripcion = Descripcion,
                UsuarioId = UsuarioId
            };
        }

    }
}