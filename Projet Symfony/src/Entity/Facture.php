<?php

namespace App\Entity;

use App\Repository\FactureRepository;
use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Integer;

/**
 * @ORM\Entity(repositoryClass=FactureRepository::class)
 */
class Facture
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="float")
     */
    private $prixTotale;

    /**
     * @ORM\ManyToOne(targetEntity=Materiel::class, inversedBy="factures")
     * @ORM\JoinColumn(nullable=false)
     */
    private $materiel;


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrixTotale(): ?float
    {
        return $this->prixTotale;
    }


    public function setPrixTotale(float $prixTotale): self
    {
        $this->prixTotale = $prixTotale;

        return $this;
    }

    public function getMateriel(): ?Materiel
    {
        return $this->materiel;
    }

    public function setMateriel(?Materiel $materiel): self
    {
        $this->materiel = $materiel;

        return $this;
    }

}
